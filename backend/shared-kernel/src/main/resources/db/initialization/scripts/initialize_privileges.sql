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
