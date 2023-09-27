create or replace function create_person(_first_name text, _last_name text, _phone_number text) returns text as
$$
declare
    _id text;

begin

    insert into ur_person(id, date_created, date_modified, first_name, last_name, phone_number)
    values(uuid_generate_v4(), now(), now(), _first_name, _last_name, _phone_number)
    returning id into _id;

    return _id;

end

$$ language plpgsql;

create or replace function create_user_if_not_exists(
    _email text,
    _password text,
    _first_name text,
    _last_name text,
    _phone_number text,
    _roles text[],
    _daily_check_ins text[],
    _credit_balance double precision,
    _credit_debt double precision) returns void as
$$
declare
    _user_id text;
    _role text;
    _person_id text;
    _daily_check_in text;
begin

    if not exists(select id from ur_user where email = lower(_email)) then
        _person_id = create_person(_first_name, _last_name, _phone_number);

        insert into ur_user(id, email, password, enabled, date_created, date_modified, ur_person_id, credit_balance, credit_debt)
        values (uuid_generate_v4(), _email, _password, true, now(), now(), _person_id, _credit_balance, _credit_debt)
        returning id into _user_id;

        foreach _role in array _roles loop
                insert into ur_user_role(id, ur_user_id, ur_role_id)
                values (uuid_generate_v4(), _user_id, find_role_id_by_name(_role));
            end loop;

        foreach _daily_check_in in array _daily_check_ins loop
                insert into mm_user_daily_check_in(id, ur_user_id, mm_daily_check_in_id)
                values (uuid_generate_v4(), _user_id, find_daily_check_in_id_by_label(_daily_check_in));
            end loop;

    end if;

end

$$ language plpgsql;

select create_user_if_not_exists('superadmin@emt.io','$2a$12$jg1BFg9nPNh6CGvs8aLH8uctGdWrQFQYvQtZm3mza8OjXHWJv/rdi','EMT', 'Super Admin', '070000000',
    array['ROLE_SUPER_ADMIN'], array['DAY_1', 'DAY_2', 'DAY_3', 'DAY_4', 'DAY_5', 'DAY_6', 'DAY_7'], 0.0, 0.0);
select create_user_if_not_exists('admin@emt.io','$2a$12$HOv3AukHWA7m.1pdDYuvXODuU2lq4c4GU4rveqLWHDS7v8zDYTERy','EMT', 'Admin', '071000000',
                                           array['ROLE_ADMIN'], array['DAY_1', 'DAY_2', 'DAY_3', 'DAY_4', 'DAY_5', 'DAY_6', 'DAY_7'], 0.0, 0.0);
select create_user_if_not_exists('customer@emt.io','$2a$12$pKQiFD9LtQBCZwswOCRUZe.Q2yXRsMmfprZf7YkdZ0LKPdSI/5XiG','EMT', 'Customer', '072000000',
                                           array['ROLE_CUSTOMER'], array['DAY_1', 'DAY_2', 'DAY_3', 'DAY_4', 'DAY_5', 'DAY_6', 'DAY_7'], 0.0, 0.0);

