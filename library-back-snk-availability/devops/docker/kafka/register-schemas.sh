register_schema() {

    schema_file=$1

    echo "Registering schema $schema_file"

    schema=$(cat $schema_file | sed 's/"/\\"/g' | tr -d '\n')

    body_data=$(echo "{\"schema\": \"$schema\"}")

    until curl -X POST -H "Content-Type: application/vnd.schemaregistry.v1+json" --data "$body_data" http://schema-registry:8081/subjects/"${schema_file%.*}"/versions; do
        retry=$((retry+1))
        if [ "$retry" -ge 100 ]; then
          echo "Register schema failed after all retries."
          exit 1
        fi
        echo "Register schema failed retrying in 5 seconds..."
        sleep 5
    done
}

scan_directory() {

    cd avro-schema

    for file in *; do
        # Verifica si el archivo es un file y si su nombre no es "register_schemas.sh"
        if [ -f "$file" ] && [ "$file" != "register-schemas.sh" ]; then
            register_schema $file
        fi

    done
}

scan_directory
