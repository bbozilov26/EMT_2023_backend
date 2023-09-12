create or replace function daily_check_ins.find_daily_check_in_id_by_label(_label text) returns bigint as
$$
declare
    _id bigint;

begin
    select id into _id
    from daily_check_ins.mm_daily_check_in
    where label = _label;

    return _id;
end
$$ language plpgsql;

create or replace function daily_check_ins.create_daily_check_in_if_not_exists(_label text, _description text, _daily_reward double precision) returns void as
$$
begin
    if not exists(select id from daily_check_ins.mm_daily_check_in where label = _label) then
        insert into daily_check_ins.mm_daily_check_in(label, description, daily_reward)
        values (_label, _description, _daily_reward);
    end if;
end

$$ language plpgsql;

select daily_check_ins.create_daily_check_in_if_not_exists('DAY_1', 'First day check in', 10.0);
select daily_check_ins.create_daily_check_in_if_not_exists('DAY_2', 'Second day check in', 25.0);
select daily_check_ins.create_daily_check_in_if_not_exists('DAY_3', 'Third day check in', 40.0);
select daily_check_ins.create_daily_check_in_if_not_exists('DAY_4', 'Fourth day check in', 55.0);
select daily_check_ins.create_daily_check_in_if_not_exists('DAY_5', 'Fifth day check in', 70.0);
select daily_check_ins.create_daily_check_in_if_not_exists('DAY_6', 'Sixth day check in', 85.0);
select daily_check_ins.create_daily_check_in_if_not_exists('DAY_7', 'Seventh day check in', 100.0);create or replace function userroles.find_privilege_id_by_name(_label text) returns bigint as
$$
declare
    _id bigint;

begin
    select id into _id
    from userroles.ur_privilege
    where label = _label;

    return _id;
end
$$ language plpgsql;

create or replace function userroles.create_privilege_if_not_exists(_privilege text, _label text) returns void as
$$
begin

    if not exists(select id from userroles.ur_privilege where privilege = _privilege) then
        insert into userroles.ur_privilege(privilege, label)
        values (_privilege, _label);
    end if;

end

$$ language plpgsql;

select userroles.create_privilege_if_not_exists('Reads users', 'READ_USERS');
select userroles.create_privilege_if_not_exists('Manages users', 'MANAGE_USERS');
select userroles.create_privilege_if_not_exists('Reads orders', 'READ_ORDERS');
select userroles.create_privilege_if_not_exists('Manages orders', 'MANAGE_ORDERS');
select userroles.create_privilege_if_not_exists('Reads quiz', 'READ_QUIZ');
select userroles.create_privilege_if_not_exists('Manages quiz', 'MANAGE_QUIZ');
select userroles.create_privilege_if_not_exists('Reads daily check ins', 'READ_DAILY_CHECK_INS');
select userroles.create_privilege_if_not_exists('Manages daily check ins', 'MANAGE_DAILY_CHECK_INS');
select userroles.create_privilege_if_not_exists('Reads products', 'READ_PRODUCTS');
select userroles.create_privilege_if_not_exists('Manages products', 'MANAGE_PRODUCTS');
create or replace function userroles.find_role_id_by_name(_label text) returns bigint as
$$
declare
    _id bigint;

begin
    select id into _id
    from userroles.ur_role
    where label = _label;

    return _id;
end
$$ language plpgsql;

create or replace function userroles.create_role_if_not_exists(_role text, _label text) returns void as
$$
begin
    if not exists(select id from userroles.ur_role where role = _role) then
        insert into userroles.ur_role(role, label)
        values (_role, _label);
    end if;
end

$$ language plpgsql;

select userroles.create_role_if_not_exists('Super administrator', 'ROLE_SUPER_ADMIN');
select userroles.create_role_if_not_exists('Administrator', 'ROLE_ADMIN');
select userroles.create_role_if_not_exists('Customer', 'ROLE_CUSTOMER');
create or replace function userroles.create_role_privilege_if_not_exists(_role_id bigint, _privilege_id bigint) returns void as
$$
begin
    if not exists(select id from userroles.ur_role_privilege where ur_role_id = _role_id and ur_privilege_id = _privilege_id) then
        insert into userroles.ur_role_privilege(ur_role_id, ur_privilege_id)
        values (_role_id, _privilege_id);
    end if;
end

$$ language plpgsql;

-- Super administrator privileges
select userroles.create_role_privilege_if_not_exists(userroles.FIND_ROLE_ID_BY_NAME('ROLE_SUPER_ADMIN'),userroles.FIND_PRIVILEGE_ID_BY_NAME('READ_USERS'));
select userroles.create_role_privilege_if_not_exists(userroles.FIND_ROLE_ID_BY_NAME('ROLE_SUPER_ADMIN'),userroles.FIND_PRIVILEGE_ID_BY_NAME('READ_ORDERS'));
select userroles.create_role_privilege_if_not_exists(userroles.FIND_ROLE_ID_BY_NAME('ROLE_SUPER_ADMIN'),userroles.FIND_PRIVILEGE_ID_BY_NAME('READ_QUIZ'));
select userroles.create_role_privilege_if_not_exists(userroles.FIND_ROLE_ID_BY_NAME('ROLE_SUPER_ADMIN'),userroles.FIND_PRIVILEGE_ID_BY_NAME('READ_PRODUCTS'));
select userroles.create_role_privilege_if_not_exists(userroles.FIND_ROLE_ID_BY_NAME('ROLE_SUPER_ADMIN'),userroles.FIND_PRIVILEGE_ID_BY_NAME('READ_DAILY_CHECK_INS'));

select userroles.create_role_privilege_if_not_exists(userroles.FIND_ROLE_ID_BY_NAME('ROLE_SUPER_ADMIN'),userroles.FIND_PRIVILEGE_ID_BY_NAME('MANAGE_USERS'));
select userroles.create_role_privilege_if_not_exists(userroles.FIND_ROLE_ID_BY_NAME('ROLE_SUPER_ADMIN'),userroles.FIND_PRIVILEGE_ID_BY_NAME('MANAGE_ORDERS'));
select userroles.create_role_privilege_if_not_exists(userroles.FIND_ROLE_ID_BY_NAME('ROLE_SUPER_ADMIN'),userroles.FIND_PRIVILEGE_ID_BY_NAME('MANAGE_QUIZ'));
select userroles.create_role_privilege_if_not_exists(userroles.FIND_ROLE_ID_BY_NAME('ROLE_SUPER_ADMIN'),userroles.FIND_PRIVILEGE_ID_BY_NAME('MANAGE_PRODUCTS'));
select userroles.create_role_privilege_if_not_exists(userroles.FIND_ROLE_ID_BY_NAME('ROLE_SUPER_ADMIN'),userroles.FIND_PRIVILEGE_ID_BY_NAME('MANAGE_DAILY_CHECK_INS'));

-- Administrator privileges
select userroles.create_role_privilege_if_not_exists(userroles.FIND_ROLE_ID_BY_NAME('ROLE_ADMIN'),userroles.FIND_PRIVILEGE_ID_BY_NAME('READ_USERS'));
select userroles.create_role_privilege_if_not_exists(userroles.FIND_ROLE_ID_BY_NAME('ROLE_ADMIN'),userroles.FIND_PRIVILEGE_ID_BY_NAME('READ_ORDERS'));
select userroles.create_role_privilege_if_not_exists(userroles.FIND_ROLE_ID_BY_NAME('ROLE_ADMIN'),userroles.FIND_PRIVILEGE_ID_BY_NAME('READ_QUIZ'));
select userroles.create_role_privilege_if_not_exists(userroles.FIND_ROLE_ID_BY_NAME('ROLE_ADMIN'),userroles.FIND_PRIVILEGE_ID_BY_NAME('READ_PRODUCTS'));
select userroles.create_role_privilege_if_not_exists(userroles.FIND_ROLE_ID_BY_NAME('ROLE_ADMIN'),userroles.FIND_PRIVILEGE_ID_BY_NAME('READ_DAILY_CHECK_INS'));

select userroles.create_role_privilege_if_not_exists(userroles.FIND_ROLE_ID_BY_NAME('ROLE_ADMIN'),userroles.FIND_PRIVILEGE_ID_BY_NAME('MANAGE_USERS'));
select userroles.create_role_privilege_if_not_exists(userroles.FIND_ROLE_ID_BY_NAME('ROLE_ADMIN'),userroles.FIND_PRIVILEGE_ID_BY_NAME('MANAGE_ORDERS'));
select userroles.create_role_privilege_if_not_exists(userroles.FIND_ROLE_ID_BY_NAME('ROLE_ADMIN'),userroles.FIND_PRIVILEGE_ID_BY_NAME('MANAGE_QUIZ'));
select userroles.create_role_privilege_if_not_exists(userroles.FIND_ROLE_ID_BY_NAME('ROLE_ADMIN'),userroles.FIND_PRIVILEGE_ID_BY_NAME('MANAGE_PRODUCTS'));
select userroles.create_role_privilege_if_not_exists(userroles.FIND_ROLE_ID_BY_NAME('ROLE_ADMIN'),userroles.FIND_PRIVILEGE_ID_BY_NAME('MANAGE_DAILY_CHECK_INS'));

-- Customer privileges
select userroles.create_role_privilege_if_not_exists(userroles.FIND_ROLE_ID_BY_NAME('ROLE_CUSTOMER'),userroles.FIND_PRIVILEGE_ID_BY_NAME('READ_ORDERS'));
select userroles.create_role_privilege_if_not_exists(userroles.FIND_ROLE_ID_BY_NAME('ROLE_CUSTOMER'),userroles.FIND_PRIVILEGE_ID_BY_NAME('READ_QUIZ'));
select userroles.create_role_privilege_if_not_exists(userroles.FIND_ROLE_ID_BY_NAME('ROLE_CUSTOMER'),userroles.FIND_PRIVILEGE_ID_BY_NAME('READ_PRODUCTS'));
select userroles.create_role_privilege_if_not_exists(userroles.FIND_ROLE_ID_BY_NAME('ROLE_CUSTOMER'),userroles.FIND_PRIVILEGE_ID_BY_NAME('READ_DAILY_CHECK_INS'));create or replace function userroles.create_person(_first_name text, _last_name text, _phone_number text) returns bigint as
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

create or replace function userroles.create_user_if_not_exists(
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
    _user_id bigint;
    _role text;
    _person_id bigint;
    _daily_check_in text;
begin

    if not exists(select id from userroles.ur_user where email = lower(_email)) then
        _person_id = userroles.create_person(_first_name, _last_name, _phone_number);

        insert into userroles.ur_user(email, password, enabled, date_created, date_modified, ur_person_id, credit_balance, credit_debt)
        values (_email, _password, true, now(), now(), _person_id, _credit_balance, _credit_debt)
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
    array['ROLE_SUPER_ADMIN'], array['DAY_1', 'DAY_2', 'DAY_3', 'DAY_4', 'DAY_5', 'DAY_6', 'DAY_7'], 0.0, 0.0);
select userroles.create_user_if_not_exists('admin@emt.io','','EMT', 'Admin', '071000000',
                                           array['ROLE_ADMIN'], array['DAY_1', 'DAY_2', 'DAY_3', 'DAY_4', 'DAY_5', 'DAY_6', 'DAY_7'], 0.0, 0.0);
select userroles.create_user_if_not_exists('customer@emt.io','','EMT', 'Customer', '072000000',
                                           array['ROLE_CUSTOMER'], array['DAY_1', 'DAY_2', 'DAY_3', 'DAY_4', 'DAY_5', 'DAY_6', 'DAY_7'], 0.0, 0.0);

