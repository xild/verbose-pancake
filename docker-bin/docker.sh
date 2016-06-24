clear
version= "cat pom.xml | grep -m1 \<version\> | awk '{ gsub("<version>","",$1); gsub("-SNAPSHOT","",$1); gsub("</version>","",$1); print $1}'"

