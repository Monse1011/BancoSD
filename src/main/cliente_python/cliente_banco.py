from zeep import Client

print("\n----- CLIENTE SISTEMA BANCARIO DISTRIBUIDO -----")

bank_wsdl = "http://localhost:8080/BankSoap?wsdl"

bank_client = Client(bank_wsdl)

print("\n*Conectado al servidor del banco*")

# Crear cuentas
print("\n- Crear cuentas -")
print("Creando cuenta para Joe con saldo inicial de 1000...")
print(bank_client.service.createAccount("5", "Joe", 1000))
print("\nCreando cuenta para Elena con saldo inicial de 500...")
print(bank_client.service.createAccount("6", "Elena", 500))

# Depositar dinero
print("\n- Depositar dinero -") 
print("Depositando 200 en la cuenta de Joe...")
print(bank_client.service.deposit("5", 200))

# Transferencia
print("\n- Realizar transferencia -")
print("Transferiendo 150 de la cuenta de Joe a la cuenta de Elena...")
print(bank_client.service.transfer("5", "6", 150))

# Consultar saldos
print("\n- Consultar saldos -")
print("\nConsultando saldo en palabras...")
palabras_wsdl= "https://www.dataaccess.com/webservicesserver/NumberConversion.wso?WSDL"
palabras_client = Client(palabras_wsdl)
saldo1 = bank_client.service.getBalance("5")
print("\nSaldo Joe: $", saldo1)
saldo1_palabras = palabras_client.service.NumberToWords(saldo1)
print("Saldo Joe en palabras (inglés):", saldo1_palabras)
saldo2 = bank_client.service.getBalance("6")
print("\nSaldo Elena: $", saldo2)
saldo2_palabras = palabras_client.service.NumberToWords(saldo2)
print("Saldo Elena en palabras (inglés):", saldo2_palabras)

# Consultar información de país
print("\nConsultando información de país...")
country_wsdl = "http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso?WSDL"
country_client = Client(country_wsdl)
capital = country_client.service.CapitalCity("MX")
currency = country_client.service.CountryCurrency("MX")
print("País: México")
print("Capital:", capital)
print("Moneda:", currency.sName)

# Informe final
print("\n----- INFORME FINAL -----")

print("Cliente: Joe")
print("Saldo final:", saldo1)
print("Saldo final en palabras:", saldo1_palabras)
print("País consultado: México")
print("Capital:", capital)
print("Moneda:", currency.sName)

print("\nCliente: Elena")
print("Saldo final:", saldo2)
print("Saldo final en palabras:", saldo2_palabras)
print("País consultado: México")
print("Capital:", capital)
print("Moneda:", currency.sName)

print("\nSistema distribuido funcionando correctamente")