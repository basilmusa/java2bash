# Check if {{processName}} is running
if pgrep {{processName | escape(strategy="bashDoubleQuotes") }} > /dev/null
then
    echo {{processName | escape(strategy="bashDoubleQuotes") }}" is running ... [ OK ]";
else
    {{PROCESS_NOT_RUNNING}}
fi