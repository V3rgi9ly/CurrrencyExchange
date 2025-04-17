# Currency Exchange
***

 REST API for describing currencies and exchange rates. Allows you to view and edit lists of currencies and exchange rates, and perform calculations for converting arbitrary amounts from one currency to another.

## Motivation of the project
*  Getting to know MVC pattern
*  REST API - proper naming of resources, use of HTTP response codes
*  basic syntax for creating tables

## Stack
> Java EE, Tomcat, SQLite, Maven

## Links
*  [Java Roadmap by Sergey Zhukov](https://zhukovsd.github.io/java-backend-learning-course/)
*  [Project "Currency Exchange"](https://zhukovsd.github.io/java-backend-learning-course/projects/currency-exchange/)

## Currencies
***
### GET /currencies
* Returns a list of all currencies.
```
[
  {
    "id": 1,
    "code": "USD",
    "fullname": "US Dollar",
    "sign": "$"
  },
  {
    "id": 2,
    "code": "RUB",
    "fullname": "Russian Ruble",
    "sign": "₽"
  },
  {
    "id": 3,
    "code": "EUR",
    "fullname": "Euro",
    "sign": "€"
  },
  {
    "id": 4,
    "code": "CNY",
    "fullname": "Yuan",
    "sign": "¥"
  },
  {
    "id": 5,
    "code": "GBP",
    "fullname": "Pound Sterling",
    "sign": "£"
  },
  {
    "id": 6,
    "code": "BRL",
    "fullname": "Brazilian Real ",
    "sign": "R"
  },
  {
    "id": 7,
    "code": "PAB",
    "fullname": "Balboa",
    "sign": "B/"
  }
]
```

### GET /currencies/USD
* Returns a specific currency. The currency code is specified in the request address.
```
{
  "id": 1,
  "code": "USD",
  "fullname": "US Dollar",
  "sign": "$"
}
```

### POST /currencies
* Adding a new currency to the database. The data is transmitted in the request body as form fields (x-www-form-urlencoded). Form fields - name, code, sign

>An example of a response is a JSON representation of a record inserted into the database, including its ID:
```
{
   "id": 7,
    "code": "PAB",
    "fullname": "Balboa",
    "sign": "B/"
}
```

## ExchangeRates
***
### GET /exchangeRates
* Getting a list of all exchange rates

```
[
  {
    "id": 9,
    "BaseCurrencyid": {
      "id": 1,
      "code": "USD",
      "fullname": "US Dollar",
      "sign": "$"
    },
    "targetCurrencyid": {
      "id": 2,
      "code": "RUB",
      "fullname": "Russian Ruble",
      "sign": "₽"
    },
    "rate": 8.40
  },
  {
    "id": 2,
    "BaseCurrencyid": {
      "id": 2,
      "code": "RUB",
      "fullname": "Russian Ruble",
      "sign": "₽"
    },
    "targetCurrencyid": {
      "id": 1,
      "code": "USD",
      "fullname": "US Dollar",
      "sign": "$"
    },
    "rate": 0.01
  },
  {
    "id": 3,
    "BaseCurrencyid": {
      "id": 1,
      "code": "USD",
      "fullname": "US Dollar",
      "sign": "$"
    },
    "targetCurrencyid": {
      "id": 3,
      "code": "EUR",
      "fullname": "Euro",
      "sign": "€"
    },
    "rate": 0.92
  },
  {
    "id": 4,
    "BaseCurrencyid": {
      "id": 2,
      "code": "RUB",
      "fullname": "Russian Ruble",
      "sign": "₽"
    },
    "targetCurrencyid": {
      "id": 3,
      "code": "EUR",
      "fullname": "Euro",
      "sign": "€"
    },
    "rate": 0.01
  }
```

### GET /exchangeRate/USDRUB
* Getting a specific exchange rate. The currency pair is set by consecutive currency codes in the request address

```
{
  "id": 9,
  "BaseCurrencyid": {
    "id": 1,
    "code": "USD",
    "fullname": "US Dollar",
    "sign": "$"
  },
  "targetCurrencyid": {
    "id": 2,
    "code": "RUB",
    "fullname": "Russian Ruble",
    "sign": "₽"
  },
  "rate": 8.40
}
```

### POST /exchangeRates
* Adding a new exchange rate to the database. The data is transferred to other sections (x-www-form-urlencoded). The norm fields are the base currency code, the target currency code, and the exchange rate
>An example of a response is a JSON representation of a record inserted into the database, including its ID:
```
{
   "id": 0,
    "baseCurrency": {
        "id": 0,
        "name": "US Dollar",
        "code": "USD",
        "sign": "$"
    },
    "targetCurrency": {
        "id": 1,
        "name": "Euro",
        "code": "EUR",
        "sign": "€"
    },
    "rate": 0.99
```

### /exchangeRate/USDRUB
* Updating the existing exchange rate in the database. The currency pair is set by consecutive currency codes in the request address. The data is transmitted in the request body as form fields (x-www-form-urlencoded). The only field in the form is rate
>The response example is a JSON representation of the updated record in the database, including its ID:

```
{
  "id": 9,
  "BaseCurrencyid": {
    "id": 1,
    "code": "USD",
    "fullname": "US Dollar",
    "sign": "$"
  },
  "targetCurrencyid": {
    "id": 2,
    "code": "RUB",
    "fullname": "Russian Ruble",
    "sign": "₽"
  },
  "rate": 8.49
}
```

### GET /exchange?from=GBP&to=CNY&amount=10
* Calculation of the transfer of a certain amount of funds from one currency to another

```
{
  "BaseCurrencyid": {
    "id": 5,
    "code": "GBP",
    "fullname": "Pound Sterling",
    "sign": "£"
  },
  "targetCurrencyid": {
    "id": 4,
    "code": "CNY",
    "fullname": "Yuan",
    "sign": "¥"
  },
  "rate": 9.49,
  "amount": 10.0,
  "convertedAmount": 94.90
}
```


## Exceptions
***

For all requests, in case of an error, the response looks like this

```
{
  "message": "Currencies not found"
}
```

The value of the message depends on which error occurred.
