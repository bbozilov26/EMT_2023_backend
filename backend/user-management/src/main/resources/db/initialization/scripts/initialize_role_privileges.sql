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
call userroles.create_role_privilege_if_not_exists(userroles.FIND_ROLE_ID_BY_NAME('ROLE_CUSTOMER'),userroles.FIND_PRIVILEGE_ID_BY_NAME('READ_DAILY_CHECK_INS'));