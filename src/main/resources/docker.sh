version=`cat pom.xml | grep -m1 \<version\> | awk '{ gsub("<version>","",$1); gsub("-SNAPSHOT","",$1); gsub("</version>","",$1); print $1}'`
mvn clean package
docker run --name mysqldb -e MYSQL_USER=mysql -e MYSQL_PASSWORD=mysql -e MYSQL_DATABASE=sample -e MYSQL_ROOT_PASSWORD=supersecret -d mysql
sudo docker build -t verbose-pancake-$version .
sudo docker run --link mysqldb:db -p 8082:8082 -t verbose-pancake-$version
