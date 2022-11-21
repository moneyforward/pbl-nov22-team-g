# crontab
sql_cmd=UPDATE borrow SET Status='done' WHERE Status='pending' AND EndDate < NOW()
mysql --user=user --password=pass --excute ${sql_cmd}