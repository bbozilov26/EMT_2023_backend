create or replace procedure userroles.create_person(in input_first_name varchar(80), in input_last_name varchar(80), in input_phone_number varchar(80), out person_id bigint)
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

