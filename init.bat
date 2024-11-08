vagrant up --provision-with setup
vagrant provision --provision-with initDB
vagrant provision --provision-with java
vagrant provision --provision-with python
vagrant provision --provision-with run-all
cmd /k