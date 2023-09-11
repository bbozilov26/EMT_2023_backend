create or replace function userroles.find_privilege_id_by_name(_label text) returns bigint as
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
