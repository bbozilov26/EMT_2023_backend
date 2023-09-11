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
select userroles.create_role_privilege_if_not_exists(userroles.FIND_ROLE_ID_BY_NAME('ROLE_CUSTOMER'),userroles.FIND_PRIVILEGE_ID_BY_NAME('READ_DAILY_CHECK_INS'));