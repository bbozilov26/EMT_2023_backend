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
