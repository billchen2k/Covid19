## 部署文档


## 安装数据库

```bash
wget https://dev.mysql.com/get/mysql-apt-config_0.8.15-1_all.deb
dpkg -i mysql-apt-config_0.8.15-1_all.deb
```
在这里选择 8.0 版本：

![](https://billc.oss-cn-shanghai.aliyuncs.com/img/2020-04-17-034545.jpg)

```bash
apt update
apt install mysql-server
```

输入 `mysql --version` 检查版本，MySQL 8.0 安装成功。

```
root@COVID19:~/Downloads# mysql --version
mysql  Ver 8.0.19 for Linux on x86_64 (MySQL Community Server - GPL)
```

在 MySQL 中授予远程访问权限：
```sql
CREATE USER 'covid19'@'%' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON *.* TO 'covid19'@'%' WITH GRANT OPTION;
flush privileges;
```
