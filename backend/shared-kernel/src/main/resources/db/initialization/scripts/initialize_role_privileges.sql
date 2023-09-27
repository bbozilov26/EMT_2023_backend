create or replace function create_role_privilege_if_not_exists(_role_id text, _privilege_id text) returns void as
$$
begin
    if not exists(select id from ur_role_privilege where ur_role_id = _role_id and ur_privilege_id = _privilege_id) then
        insert into ur_role_privilege(id, ur_role_id, ur_privilege_id)
        values (uuid_generate_v4(), _role_id, _privilege_id);
    end if;
end

$$ language plpgsql;

-- Super administrator privileges
select create_role_privilege_if_not_exists(FIND_ROLE_ID_BY_NAME('ROLE_SUPER_ADMIN'), FIND_PRIVILEGE_ID_BY_NAME('READ_USERS'));
select create_role_privilege_if_not_exists(FIND_ROLE_ID_BY_NAME('ROLE_SUPER_ADMIN'), FIND_PRIVILEGE_ID_BY_NAME('READ_ORDERS'));
select create_role_privilege_if_not_exists(FIND_ROLE_ID_BY_NAME('ROLE_SUPER_ADMIN'), FIND_PRIVILEGE_ID_BY_NAME('READ_QUIZ'));
select create_role_privilege_if_not_exists(FIND_ROLE_ID_BY_NAME('ROLE_SUPER_ADMIN'), FIND_PRIVILEGE_ID_BY_NAME('READ_PRODUCTS'));
select create_role_privilege_if_not_exists(FIND_ROLE_ID_BY_NAME('ROLE_SUPER_ADMIN'), FIND_PRIVILEGE_ID_BY_NAME('READ_DAILY_CHECK_INS'));

select create_role_privilege_if_not_exists(FIND_ROLE_ID_BY_NAME('ROLE_SUPER_ADMIN'), FIND_PRIVILEGE_ID_BY_NAME('MANAGE_USERS'));
select create_role_privilege_if_not_exists(FIND_ROLE_ID_BY_NAME('ROLE_SUPER_ADMIN'), FIND_PRIVILEGE_ID_BY_NAME('MANAGE_ORDERS'));
select create_role_privilege_if_not_exists(FIND_ROLE_ID_BY_NAME('ROLE_SUPER_ADMIN'), FIND_PRIVILEGE_ID_BY_NAME('MANAGE_QUIZ'));
select create_role_privilege_if_not_exists(FIND_ROLE_ID_BY_NAME('ROLE_SUPER_ADMIN'), FIND_PRIVILEGE_ID_BY_NAME('MANAGE_PRODUCTS'));
select create_role_privilege_if_not_exists(FIND_ROLE_ID_BY_NAME('ROLE_SUPER_ADMIN'), FIND_PRIVILEGE_ID_BY_NAME('MANAGE_DAILY_CHECK_INS'));

-- Administrator privileges
select create_role_privilege_if_not_exists(FIND_ROLE_ID_BY_NAME('ROLE_ADMIN'), FIND_PRIVILEGE_ID_BY_NAME('READ_ORDERS'));
select create_role_privilege_if_not_exists(FIND_ROLE_ID_BY_NAME('ROLE_ADMIN'), FIND_PRIVILEGE_ID_BY_NAME('READ_QUIZ'));
select create_role_privilege_if_not_exists(FIND_ROLE_ID_BY_NAME('ROLE_ADMIN'), FIND_PRIVILEGE_ID_BY_NAME('READ_PRODUCTS'));

select create_role_privilege_if_not_exists(FIND_ROLE_ID_BY_NAME('ROLE_ADMIN'), FIND_PRIVILEGE_ID_BY_NAME('MANAGE_ORDERS'));
select create_role_privilege_if_not_exists(FIND_ROLE_ID_BY_NAME('ROLE_ADMIN'), FIND_PRIVILEGE_ID_BY_NAME('MANAGE_QUIZ'));
select create_role_privilege_if_not_exists(FIND_ROLE_ID_BY_NAME('ROLE_ADMIN'), FIND_PRIVILEGE_ID_BY_NAME('MANAGE_PRODUCTS'));

-- Customer privileges
select create_role_privilege_if_not_exists(FIND_ROLE_ID_BY_NAME('ROLE_CUSTOMER'), FIND_PRIVILEGE_ID_BY_NAME('READ_ORDERS'));
select create_role_privilege_if_not_exists(FIND_ROLE_ID_BY_NAME('ROLE_CUSTOMER'), FIND_PRIVILEGE_ID_BY_NAME('READ_QUIZ'));
select create_role_privilege_if_not_exists(FIND_ROLE_ID_BY_NAME('ROLE_CUSTOMER'), FIND_PRIVILEGE_ID_BY_NAME('READ_PRODUCTS'));
select create_role_privilege_if_not_exists(FIND_ROLE_ID_BY_NAME('ROLE_CUSTOMER'), FIND_PRIVILEGE_ID_BY_NAME('READ_DAILY_CHECK_INS'));

