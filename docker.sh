version=`cat pom.xml | grep -m1 \<version\> | awk '{ gsub("<version>","",$1); gsub("-SNAPSHOT","",$1); gsub("</version>","",$1); print $1}'`
mvn clean package
sudo docker build -t verbose-pancake-$version .
sudo docker run -p 8082:8082 -t verbose-pancake-$version
