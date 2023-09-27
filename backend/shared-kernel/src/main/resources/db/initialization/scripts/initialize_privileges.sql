create or replace function find_privilege_id_by_name(_label text) returns text as
$$
declare
    _id text;

begin
    select id into _id
    from ur_privilege
    where label = _label;

    return _id;
end
$$ language plpgsql;

create or replace function create_privilege_if_not_exists(_privilege text, _label text) returns void as
$$
begin

    if not exists(select id from ur_privilege where privilege = _privilege) then
        insert into ur_privilege(id, privilege, label)
        values (uuid_generate_v4(), _privilege, _label);
    end if;

end

$$ language plpgsql;

select create_privilege_if_not_exists('Reads users', 'READ_USERS');
select create_privilege_if_not_exists('Manages users', 'MANAGE_USERS');
select create_privilege_if_not_exists('Reads orders', 'READ_ORDERS');
select create_privilege_if_not_exists('Manages orders', 'MANAGE_ORDERS');
select create_privilege_if_not_exists('Reads quiz', 'READ_QUIZ');
select create_privilege_if_not_exists('Manages quiz', 'MANAGE_QUIZ');
select create_privilege_if_not_exists('Reads daily check ins', 'READ_DAILY_CHECK_INS');
select create_privilege_if_not_exists('Manages daily check ins', 'MANAGE_DAILY_CHECK_INS');
select create_privilege_if_not_exists('Reads products', 'READ_PRODUCTS');
select create_privilege_if_not_exists('Manages products', 'MANAGE_PRODUCTS');

