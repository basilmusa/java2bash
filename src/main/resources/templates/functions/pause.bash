pause ()
{
REPLY=Y
echo -e "Would you like to continue? [y/n]"
while [ "$REPLY" == "Y" ] || [ "$REPLY" != "y" ]
do
  read -n1 -s
      case "$REPLY" in
      "n")  exit;;
      "N")  exit;;
      * )   ;;
esac
done
}
