create or replace function userroles.find_privilege_id_by_name(input_privilege varchar(80))
    returns bigint
    language sql
begin
    declare new_id bigint;
    select id into new_id from userroles.UR_PRIVILEGE where privilege = input_privilege;
    return new_id;
end;

create or replace procedure userroles.create_privilege_if_not_exists(in input_description varchar(5000), in input_label varchar(5000), in input_privilege varchar(80))
    language sql
    modifies sql data
begin
    if not exists(select id from userroles.UR_PRIVILEGE where privilege = input_privilege) then
        insert into userroles.UR_PRIVILEGE(description, label, privilege) values (input_description, input_label, input_privilege);
    else
        update userroles.UR_PRIVILEGE
        set description = input_description,
            label = input_label,
        where privilege = input_privilege;
    end if;
end;

call userroles.create_privilege_if_not_exists('Reads users', 'READ_USERS', 'READ_USERS');
call userroles.create_privilege_if_not_exists('Manages users', 'MANAGE_USERS', 'MANAGE_USERS');
call userroles.create_privilege_if_not_exists('Reads orders', 'READ_ORDERS', 'READ_ORDERS');
call userroles.create_privilege_if_not_exists('Manages orders', 'MANAGE_ORDERS', 'MANAGE_ORDERS');
call userroles.create_privilege_if_not_exists('Reads quiz', 'READ_QUIZ', 'READ_QUIZ');
call userroles.create_privilege_if_not_exists('Manages quiz', 'MANAGE_QUIZ', 'MANAGE_QUIZ');
call userroles.create_privilege_if_not_exists('Reads daily check ins', 'READ_DAILY_CHECK_INS', 'READ_DAILY_CHECK_INS');
call userroles.create_privilege_if_not_exists('Manages daily check ins', 'MANAGE_DAILY_CHECK_INS', 'MANAGE_DAILY_CHECK_INS');
call userroles.create_privilege_if_not_exists('Reads products', 'READ_PRODUCTS', 'READ_PRODUCTS');
call userroles.create_privilege_if_not_exists('Manages products', 'MANAGE_PRODUCTS', 'MANAGE_PRODUCTS');
create or replace function userroles.find_role_id_by_name(input_role varchar(80))
    returns bigint
    language sql
begin
    declare new_id bigint;
    select id into new_id from userroles.UR_ROLE where role = input_role;
    return new_id;
end;

create or replace procedure userroles.create_role_if_not_exists(in input_label varchar(5000), in input_role varchar(80))
    language sql
    modifies sql data
begin
    if not exists(select id from userroles.UR_ROLE where role = input_role) then
        insert into userroles.UR_ROLE(label, role) values (input_label, input_role);
    else
        update userroles.UR_ROLE
            set label = input_label
        where role = input_role;
    end if;
end;

call userroles.create_role_if_not_exists('Super administrator', 'ROLE_SUPER_ADMIN');
call userroles.create_role_if_not_exists('Administrator', 'ROLE_ADMIN');
call userroles.create_role_if_not_exists('Customer', 'ROLE_CUSTOMER');
create or replace procedure userroles.create_role_privilege_if_not_exists(in input_role_id bigint, in input_privilege_id bigint)
    language sql
    modifies sql data
begin
    if not exists(select id from userroles.UR_ROLE_PRIVILEGE where ur_role_id = input_role_id and ur_privilege_id = input_privilege_id) then
        insert into userroles.ur_role_privilege(ur_role_id, ur_privilege_id) values (input_role_id, input_privilege_id);
    end if;
end;

-- Super administrator privileges
call userroles.create_role_privilege_if_not_exists(userroles.FIND_ROLE_ID_BY_NAME('ROLE_SUPER_ADMIN'),userroles.FIND_PRIVILEGE_ID_BY_NAME('READ_USERS'));
call userroles.create_role_privilege_if_not_exists(userroles.FIND_ROLE_ID_BY_NAME('ROLE_SUPER_ADMIN'),userroles.FIND_PRIVILEGE_ID_BY_NAME('READ_ORDERS'));
call userroles.create_role_privilege_if_not_exists(userroles.FIND_ROLE_ID_BY_NAME('ROLE_SUPER_ADMIN'),userroles.FIND_PRIVILEGE_ID_BY_NAME('READ_QUIZ'));
call userroles.create_role_privilege_if_not_exists(userroles.FIND_ROLE_ID_BY_NAME('ROLE_SUPER_ADMIN'),userroles.FIND_PRIVILEGE_ID_BY_NAME('READ_PRODUCTS'));
call userroles.create_role_privilege_if_not_exists(userroles.FIND_ROLE_ID_BY_NAME('ROLE_SUPER_ADMIN'),userroles.FIND_PRIVILEGE_ID_BY_NAME('READ_DAILY_CHECK_INS'));

call userroles.create_role_privilege_if_not_exists(userroles.FIND_ROLE_ID_BY_NAME('ROLE_SUPER_ADMIN'),userroles.FIND_PRIVILEGE_ID_BY_NAME('MANAGE_USERS'));
call userroles.create_role_privilege_if_not_exists(userroles.FIND_ROLE_ID_BY_NAME('ROLE_SUPER_ADMIN'),userroles.FIND_PRIVILEGE_ID_BY_NAME('MANAGE_ORDERS'));
call userroles.create_role_privilege_if_not_exists(userroles.FIND_ROLE_ID_BY_NAME('ROLE_SUPER_ADMIN'),userroles.FIND_PRIVILEGE_ID_BY_NAME('MANAGE_QUIZ'));
call userroles.create_role_privilege_if_not_exists(userroles.FIND_ROLE_ID_BY_NAME('ROLE_SUPER_ADMIN'),userroles.FIND_PRIVILEGE_ID_BY_NAME('MANAGE_PRODUCTS'));
call userroles.create_role_privilege_if_not_exists(userroles.FIND_ROLE_ID_BY_NAME('ROLE_SUPER_ADMIN'),userroles.FIND_PRIVILEGE_ID_BY_NAME('MANAGE_DAILY_CHECK_INS'));

-- Administrator privileges
call userroles.create_role_privilege_if_not_exists(userroles.FIND_ROLE_ID_BY_NAME('ROLE_ADMIN'),userroles.FIND_PRIVILEGE_ID_BY_NAME('READ_USERS'));
call userroles.create_role_privilege_if_not_exists(userroles.FIND_ROLE_ID_BY_NAME('ROLE_ADMIN'),userroles.FIND_PRIVILEGE_ID_BY_NAME('READ_ORDERS'));
call userroles.create_role_privilege_if_not_exists(userroles.FIND_ROLE_ID_BY_NAME('ROLE_ADMIN'),userroles.FIND_PRIVILEGE_ID_BY_NAME('READ_QUIZ'));
call userroles.create_role_privilege_if_not_exists(userroles.FIND_ROLE_ID_BY_NAME('ROLE_ADMIN'),userroles.FIND_PRIVILEGE_ID_BY_NAME('READ_PRODUCTS'));
call userroles.create_role_privilege_if_not_exists(userroles.FIND_ROLE_ID_BY_NAME('ROLE_ADMIN'),userroles.FIND_PRIVILEGE_ID_BY_NAME('READ_DAILY_CHECK_INS'));

call userroles.create_role_privilege_if_not_exists(userroles.FIND_ROLE_ID_BY_NAME('ROLE_ADMIN'),userroles.FIND_PRIVILEGE_ID_BY_NAME('MANAGE_USERS'));
call userroles.create_role_privilege_if_not_exists(userroles.FIND_ROLE_ID_BY_NAME('ROLE_ADMIN'),userroles.FIND_PRIVILEGE_ID_BY_NAME('MANAGE_ORDERS'));
call userroles.create_role_privilege_if_not_exists(userroles.FIND_ROLE_ID_BY_NAME('ROLE_ADMIN'),userroles.FIND_PRIVILEGE_ID_BY_NAME('MANAGE_QUIZ'));
call userroles.create_role_privilege_if_not_exists(userroles.FIND_ROLE_ID_BY_NAME('ROLE_ADMIN'),userroles.FIND_PRIVILEGE_ID_BY_NAME('MANAGE_PRODUCTS'));
call userroles.create_role_privilege_if_not_exists(userroles.FIND_ROLE_ID_BY_NAME('ROLE_ADMIN'),userroles.FIND_PRIVILEGE_ID_BY_NAME('MANAGE_DAILY_CHECK_INS'));

-- Customer privileges
call userroles.create_role_privilege_if_not_exists(userroles.FIND_ROLE_ID_BY_NAME('ROLE_CUSTOMER'),userroles.FIND_PRIVILEGE_ID_BY_NAME('READ_ORDERS'));
call userroles.create_role_privilege_if_not_exists(userroles.FIND_ROLE_ID_BY_NAME('ROLE_CUSTOMER'),userroles.FIND_PRIVILEGE_ID_BY_NAME('READ_QUIZ'));
call userroles.create_role_privilege_if_not_exists(userroles.FIND_ROLE_ID_BY_NAME('ROLE_CUSTOMER'),userroles.FIND_PRIVILEGE_ID_BY_NAME('READ_PRODUCTS'));
call userroles.create_role_privilege_if_not_exists(userroles.FIND_ROLE_ID_BY_NAME('ROLE_CUSTOMER'),userroles.FIND_PRIVILEGE_ID_BY_NAME('READ_DAILY_CHECK_INS'));create or replace procedure userroles.create_person(in input_first_name varchar(80), in input_last_name varchar(80), in input_phone_number varchar(80), out person_id bigint)
    modifies sql data
begin
    select id into person_id from new table (
        insert into userroles.UR_PERSON(date_created, date_modified, first_name, last_name, phone_number)
        values (current_timestamp, current_timestamp, input_first_name, input_last_name, input_phone_number)
        );
end;

create or replace type str_array as varchar(80) array[80];
create or replace procedure userroles.create_user_if_not_exists(in input_email varchar(80), in input_password varchar(500), in input_first_name varchar(80),
                                                                in input_last_name varchar(80), in input_phone_number varchar(80), in input_role varchar(250))
    language sql
    modifies sql data
begin
    declare user_id, person_id bigint;

    if not exists(select id from userroles.ur_user where email = lower(input_email)) then
        call userroles.create_person(input_first_name, input_last_name, input_phone_number, person_id);
        insert into userroles.ur_user(email, password, enabled, date_created, date_modified, ur_person_id)
        values (input_email, input_password, true, current_timestamp, current_timestamp, person_id);
        select id into user_id from userroles.UR_USER;

        insert into userroles.UR_USER_ROLE(ur_user_id, ur_role_id)
        values (user_id, userroles.FIND_ROLE_ID_BY_NAME(input_role));
    end if;
end;

begin
    call userroles.create_user_if_not_exists('superadmin@emt.io',
                                             '',
                                             'EMT', 'Super Admin', '070000000', 'ROLE_SUPER_ADMIN');
end;

