#!/bin/bash

set -e
set -u

function create_user_and_database() {
  local database=$1
  local user=$POSTGRES_USER

  echo "Creating database '$database'"

  # Crear usuario y base
  psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
    CREATE USER $database WITH PASSWORD '$database';
    CREATE DATABASE $database OWNER $database;
    GRANT ALL PRIVILEGES ON DATABASE $database TO $user;
EOSQL

  # Conceder permisos dentro de la base
  psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$database" <<-EOSQL
    GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO $user;
    GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO $user;
    GRANT ALL PRIVILEGES ON ALL FUNCTIONS IN SCHEMA public TO $user;

    ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL PRIVILEGES ON TABLES TO $user;
    ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL PRIVILEGES ON SEQUENCES TO $user;
    ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL PRIVILEGES ON FUNCTIONS TO $user;

    GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO $database;
    GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO $database;
    GRANT ALL PRIVILEGES ON ALL FUNCTIONS IN SCHEMA public TO $database;

    ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL PRIVILEGES ON TABLES TO $database;
    ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL PRIVILEGES ON SEQUENCES TO $database;
    ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL PRIVILEGES ON FUNCTIONS TO $database;
EOSQL

  if [ -f /tmp/"$database".sql ]; then
    echo "Creating database structure"
    psql -U "$database" -d "$database" -f /tmp/"$database".sql

    if [ -f /tmp/"$database"-dataset.sql ]; then
      echo "Creating initial dataset in database"
      psql -U "$database" -d "$database" -f /tmp/"$database"-dataset.sql
    fi
  fi
}


if [ -n "$POSTGRES_MULTIPLE_DATABASES" ]; then
	echo "Multiple database creation requested: $POSTGRES_MULTIPLE_DATABASES"
	for db in $(echo $POSTGRES_MULTIPLE_DATABASES | tr ',' ' '); do
		create_user_and_database $db
	done
	echo "Multiple databases created"
fi
