#https://gist.github.com/dikkedimi/663e4db20137fe0cb5f94e1f7690aad5
#https://gist.github.com/elyssonmr/a2c27be99c19d05a6a99cbc70c825563
# -*- mode: ruby -*-
# vi: set ft=ruby :

# All Vagrant configuration is done below. The "2" in Vagrant.configure
# configures the configuration version (we support older styles for
# backwards compatibility). Please don't change it unless you know what
# you're doing.
Vagrant.configure("2") do |config|
  # The most common configuration options are documented and commented below.
  # For a complete reference, please see the online documentation at
  # https://docs.vagrantup.com.

  # Every Vagrant development environment requires a box. You can search for
  # boxes at https://vagrantcloud.com/search.
  config.vm.box = "ubuntu/jammy64"
  #config.vm.hostname = "vagrant"
  config.vm.provider :virtualbox do |vb|
    vb.name = "Django"
  end
  #ENV['LC_ALL']="de_DE.UTF-8"
  # Disable automatic box update checking. If you disable this, then
  # boxes will only be checked for updates when the user runs
  # `vagrant box outdated`. This is not recommended.
  # config.vm.box_check_update = false

  # Create a forwarded port mapping which allows access to a specific port
  # within the machine from a port on the host machine. In the example below,
  # accessing "localhost:8080" will access port 80 on the guest machine.
  # NOTE: This will enable public access to the opened port
  # config.vm.network "forwarded_port", guest: 80, host: 8080

  # Create a forwarded port mapping which allows access to a specific port
  # within the machine from a port on the host machine and only allow access
  # via 127.0.0.1 to disable public access
  config.vm.network "forwarded_port", guest: 80, host: 80, host_ip: "127.0.0.1"

  # Create a private network, which allows host-only access to the machine
  # using a specific IP.
  # config.vm.network "private_network", ip: "192.168.33.10"

  # Create a public network, which generally matched to bridged network.
  # Bridged networks make the machine appear as another physical device on
  # your network.
  # config.vm.network "public_network"

  # Share an additional folder to the guest VM. The first argument is
  # the path on the host to the actual folder. The second argument is
  # the path on the guest to mount the folder. And the optional third
  # argument is a set of non-required options.
  # config.vm.synced_folder "../data", "/vagrant_data"
  config.vm.synced_folder "html", "/var/www/html" 
  config.vm.synced_folder "conf", "/usr/conf"
  config.vm.synced_folder "InitDB", "/usr/InitDB"
  config.vm.synced_folder "Service", "/usr/Service"
  config.vm.synced_folder "Django", "/usr/Django"
  # Disable the default share of the current code directory. Doing this
  # provides improved isolation between the vagrant box and your host
  # by making sure your Vagrantfile isn't accessible to the vagrant box.
  # If you use this you may want to enable additional shared subfolders as
  # shown above.
  # config.vm.synced_folder ".", "/vagrant", disabled: true

  # Provider-specific configuration so you can fine-tune various
  # backing providers for Vagrant. These expose provider-specific options.
  # Example for VirtualBox:
  #
  config.vm.provider "virtualbox" do |vb|
  #   # Display the VirtualBox GUI when booting the machine
  #   vb.gui = true
  #
  #   # Customize the amount of memory on the VM:
    vb.memory = "4096"
    vb.cpus = 4
  end
  #
  # View the documentation for the provider you are using for more
  # information on available options.

  # Enable provisioning with a shell script. Additional provisioners such as
  # Ansible, Chef, Docker, Puppet and Salt are also available. Please see the
  # documentation for more information about their specific syntax and use.
  config.vm.provision "setup", type: "shell", inline: <<-SHELL
    # Update and upgrade the server packages.
    sudo apt-get update
    sudo apt-get -y upgrade
    # Set Ubuntu Language
    sudo locale-gen de_DE.UTF-8
    sudo update-locale LANG=de_DE.UTF-8
	  sudo timedatectl set-timezone Europe/Berlin
    sudo apt install apache2 wget vim unzip zip libapache2-mod-wsgi-py3 -y
	  sudo systemctl start apache2

	  DBUSER=root
	  DBPASSWD=root

	  sudo debconf-set-selections <<< "mysql-server mysql-server/root_password password $DBPASSWD"
	  sudo debconf-set-selections <<< "mysql-server mysql-server/root_password_again password $DBPASSWD"
	  sudo debconf-set-selections <<< "phpmyadmin phpmyadmin/dbconfig-install boolean true"
	  sudo debconf-set-selections <<< "phpmyadmin phpmyadmin/app-password-confirm password $DBPASSWD"
	  sudo debconf-set-selections <<< "phpmyadmin phpmyadmin/mysql/admin-pass password $DBPASSWD"
	  sudo debconf-set-selections <<< "phpmyadmin phpmyadmin/mysql/app-pass password $DBPASSWD"
	  sudo debconf-set-selections <<< "phpmyadmin phpmyadmin/reconfigure-webserver multiselect apache2"
	  sudo apt-get install mysql-server  -y

	  sudo mysql -uroot -p$DBPASSWD -e "ALTER USER 'root'@'localhost' IDENTIFIED WITH MYSQL_NATIVE_PASSWORD BY 'root';"
	  sudo mysql -uroot -p$DBPASSWD -e "CREATE DATABASE timeradb"
	  sudo mysql -uroot -p$DBPASSWD -e "CREATE USER 'dbmanager'@'localhost' IDENTIFIED WITH MYSQL_NATIVE_PASSWORD BY 'salt';"
	  sudo mysql -uroot -p$DBPASSWD -e "grant all privileges on timeradb.* to 'dbmanager'@'localhost';"

	  sudo apt install php -y
	  sudo apt install phpmyadmin php-mbstring php-php-gettext -y 

    sudo apt install openjdk-21-jdk -y
    export JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64
    export PATH=$PATH:$JAVA_HOME/bin

    sudo apt-get install maven -y
    export MAVEN_HOME=/usr/share/maven
    export PATH=$PATH:$MAVEN_HOME/bin

    sudo cp /usr/conf/000-default.conf /etc/apache2/sites-available/000-default.conf
    sudo a2enmod proxy
    sudo a2enmod proxy_http
    sudo a2enmod proxy_balancer
    sudo a2enmod lbmethod_byrequests
    sudo a2enmod headers
    sudo a2enmod wsgi 
    sudo systemctl restart apache2

    # Setup virtualenvwrapper
    sudo apt install python3-pip -y
    sudo apt-get install pkg-config -y
    sudo apt-get install default-libmysqlclient-dev -y
    
	  pip install virtualenvwrapper
    if ! grep -q VIRTUALENV_ALREADY_ADDED /home/vagrant/.bashrc; then
        echo "# VIRTUALENV_ALREADY_ADDED" >> /home/vagrant/.bashrc
        echo "WORKON_HOME=~/.virtualenvs" >> /home/vagrant/.bashrc
        echo "PROJECT_HOME=/vagrant" >> /home/vagrant/.bashrc
        echo "source /usr/local/bin/virtualenvwrapper.sh" >> /home/vagrant/.bashrc
    fi 
    source /usr/local/bin/virtualenvwrapper.sh  
    export WORKON_HOME="/home/vagrant/.virtualenvs"
    mkvirtualenv django_api --python=python3

    .virtualenvs/django_api/bin/python3 -m pip install Django==5.1.2
    .virtualenvs/django_api/bin/python3 -m pip install Djangorestframework==3.15.2
    .virtualenvs/django_api/bin/python3 -m pip install mysqlclient
    .virtualenvs/django_api/bin/python3 -m pip install uwsgi
  SHELL

  config.vm.provision "initDB", type: "shell", inline: <<-SHELL
    sudo mvn -s /usr/InitDB/settings.xml -f /usr/InitDB/pom.xml -DskipTests=true clean install
    sudo cp /usr/InitDB/target/InitDB*.jar /usr/InitDB/target/InitDB.jar
    java -jar /usr/InitDB/target/InitDB.jar
  SHELL
  
  config.vm.provision "python", type: "shell", inline: <<-SHELL
    cd /usr/Django
    sudo ../../home/vagrant/.virtualenvs/django_api/bin/python3 manage.py collectstatic --no-input
    cd ~
  SHELL

  config.vm.provision "java", type: "shell", inline: <<-SHELL
    sudo pkill -9 -f Service.jar
    sudo mvn -s /usr/Service/settings.xml -f /usr/Service/pom.xml -DskipTests=true clean install
    sudo cp /usr/Service/target/Service*.jar /usr/Service/target/Service.jar
  SHELL

  config.vm.provision "run", type: "shell", inline: <<-SHELL
    nohup java -jar /usr/Service/target/Service.jar > /usr/Service/log.txt 2>&1 &
    cd /usr/Django
    nohup ../../home/vagrant/.virtualenvs/django_api/bin/uwsgi --http :8000 --module profiles_project.wsgi:application > /usr/Django/log.txt 2>&1 &
    cd ~
  SHELL

end
