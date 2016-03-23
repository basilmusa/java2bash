cat << '___EOF__' > {{outputFile | escape(strategy="bashDoubleQuotes")}}

{{fileContents}}

___EOF__