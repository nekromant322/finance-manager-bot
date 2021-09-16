deploy on heroku  
mvn clean package  
heroku deploy:jar target/finance-manager-bot-0.0.1-SNAPSHOT.jar --app <app_name>

logs  
heroku logs --tail --app <app_name>

