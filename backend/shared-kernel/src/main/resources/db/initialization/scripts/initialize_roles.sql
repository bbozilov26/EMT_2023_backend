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
