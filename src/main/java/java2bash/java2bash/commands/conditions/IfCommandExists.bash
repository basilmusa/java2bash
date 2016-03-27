# Check if command name exists
if java2bash_command_exists {{commandName | escape(strategy="bashDoubleQuotes") }}; then
    {{TRUE_ACTION}}
    
else
    {{FALSE_ACTION}}

fi