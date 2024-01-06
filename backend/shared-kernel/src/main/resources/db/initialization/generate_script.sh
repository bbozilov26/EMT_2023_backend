#!/bin/bash
cat \
"scripts/create_uuid_extension.sql" \
"scripts/initialize_daily_check_ins.sql" \
"scripts/initialize_privileges.sql" \
"scripts/initialize_roles.sql" \
"scripts/initialize_role_privileges.sql" \
"scripts/initialize_users.sql" \
"scripts/initialize_quiz.sql" \
> "initialization_script.sql"