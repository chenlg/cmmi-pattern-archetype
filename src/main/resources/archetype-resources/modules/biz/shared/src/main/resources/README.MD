1. 生成别名和密码为 "serverkey"/"myPassword"的服务端证书，别名都使用小写（在keystore中存储的别名都是小写字符）， 并保存在server-keystore.jks中（改证书用来服务端解密）  
keytool -genkey -alias serverkey -validity 365 -keypass myPassword -keystore server-keystore.jks -storepass myPassword -dname "cn=serverkey" -keyalg RSA  
  
2. 自签名我们的生成的证书（正式环境应该由正式的公司来做这个步骤，比如Verisign）  
keytool -selfcert -alias serverkey -validity 365 -keystore server-keystore.jks -storepass myPassword -keypass myPassword  
  
3. 从服务端keystore中导出公钥并且命名为 key.cer  
keytool -export -alias serverkey -file serverkey.cer -keystore server-keystore.jks -storepass myPassword  
  
4. 将步骤3导出的证书导入到客户端的client-truststore.jks（用来做客户端加密）  
  
keytool -import -alias serverkey -file serverkey.cer -keystore client-truststore.jks -storepass myPassword  
  
5. 生成别名和密码为 "clientkey"/"myPassword"的客户端证书， 并保存在client-keystore.jks中（改证书用来服务端解密）  
keytool -genkey -alias clientkey -validity 365 -keypass myPassword -keystore client-keystore.jks -storepass myPassword -dname "cn=clientkey" -keyalg RSA  
  
6. 自签名我们的生成的证书（正式环境应该由正式的公司来做这个步骤，比如Verisign）  
keytool -selfcert -alias clientkey -validity 365 -keystore client-keystore.jks -storepass myPassword -keypass myPassword  
  
7. 从客户端keystore中导出公钥并且命名为 key.cer  
keytool -export -alias clientkey -file clientkey.cer -keystore client-keystore.jks -storepass myPassword  
  
8. 将步骤3导出的证书导入到服务端的server-truststore.jks（用来做客户端加密）  
  
keytool -import -alias clientkey -file clientkey.cer -keystore server-truststore.jks -storepass myPassword  