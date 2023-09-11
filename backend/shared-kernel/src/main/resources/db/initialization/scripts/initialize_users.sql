create or replace function userroles.create_person(_first_name text, _last_name text, _phone_number text) returns bigint as
$$
declare
    _id bigint;

begin

    insert into userroles.ur_person(date_created, date_modified, first_name, last_name, phone_number)
    values(now(), now(), _first_name, _last_name, _phone_number)
    returning id into _id;

    return _id;

end

$$ language plpgsql;

create or replace function userroles.create_user_if_not_exists(_email text, _password text, _first_name text, _last_name text, _phone_number text, _roles text[], _daily_check_ins text[]) returns void as
$$
declare
    _user_id bigint;
    _role text;
    _person_id bigint;
    _daily_check_in text;
begin

    if not exists(select id from userroles.ur_user where email = lower(_email)) then
        _person_id = userroles.create_person(_first_name, _last_name, _phone_number);

        insert into userroles.ur_user(email, password, enabled, date_created, date_modified, ur_person_id)
        values (_email, _password, true, now(), now(), _person_id)
        returning id into _user_id;

        foreach _role in array _roles loop
                insert into userroles.ur_user_role(ur_user_id, ur_role_id)
                values (_user_id, userroles.find_role_id_by_name(_role));
            end loop;

        foreach _daily_check_in in array _daily_check_ins loop
                insert into daily_check_ins.mm_user_daily_check_in(ur_user_id, mm_daily_check_in_id)
                values (_user_id, daily_check_ins.find_daily_check_in_id_by_label(_daily_check_in));
            end loop;

    end if;

end

$$ language plpgsql;

select userroles.create_user_if_not_exists('superadmin@emt.io','','EMT', 'Super Admin', '070000000',
    array['ROLE_SUPER_ADMIN'], array['DAY_1', 'DAY_2', 'DAY_3', 'DAY_4', 'DAY_5', 'DAY_6', 'DAY_7']);

