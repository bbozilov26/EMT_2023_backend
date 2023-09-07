#!/bin/bash
cat \
"scripts/initialize_privileges.sql" \
"scripts/initialize_roles.sql" \
"scripts/initialize_role_privileges.sql" \
"scripts/initialize_users.sql" \
> "initialization_script.sql"