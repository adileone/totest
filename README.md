valorizzare la variabile di sistema

col path al service account

https://stackoverflow.com/questions/7048216/environment-variables-in-eclipse

GOOGLE_APPLICATION_CREDENTIALS

profili:
- dev: usa application-dev.yaml
- h2: usa il db h2
- gcs-dummy: usa una classe che non fa niente simulando lo step di invio su gcs

es: 
-Dspring.profiles.active=dev,h2,gcs-dummy

valorizzare:
file:
  output: <path per scrivere il csv>
  
gsutil:
  projectId: <id progetto gcp>
  bucketname: <nome del bucket>  