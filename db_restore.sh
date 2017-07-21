#!/usr/bin/env bash
echo "Creating database 'wildlife_tracker', database: Postgres"
psql -c "CREATE DATABASE wildlife_tracker;"
psql wildlife_tracker < wildlife_tracker_db_backup.sql
psql -c "CREATE DATABASE wildlife_tracker_test WITH TEMPLATE wildlife_tracker;"
