# API Document for eTest project

## Authentication

The client must send bearer token in the Authorization header when making requests to protected resources:

```
Authorization: Bearer <token>
```

## Response Body Format
Response body of all API will have the same format as examples below:
- Success result
```json
{
    "result": {
        "id": "8265dfc2-6c02-4aee-957c-9c8189e1d753",
        "name": "New System 3",
        "description": "System 3 description. Updated."
    },
    "error": null,
    "success": true
}
```

- Error result
```json
{
    "result": null,
    "error": {
        "code": 4000000,
        "message": "System description can not be blank"
    },
    "success": false
}
```

| Field   | Type    | Description                                                                                                                                                                                           |
|---------|---------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| success | Boolean | Is true if execute API success, else, it is false                                                                                                                                                     |
| result  | Object  | Contains response data, it is null in case API return an error<br/>Format of this field is depend on each API.<br/>The Responses (with status 200) section of APIs below is description of this field |
| error   | Object  | Contains error code and message of error returned by API, it is null if API execute successfully                                                                                                      |
| code    | Integer | Code of error.<br/> Specific code of each API will de describe at Response (with staus 4xx) section in other sheet.<br/>For common error code, see API Error Code section below                       |
| message | String  | Error message, used to understand about error internally. Not show to UI                                                                                                                              |

## API Error Code
eTest API use both HTTP Status Code and Error Code to describe error happened.

A successful API will return HTTP 200 and data in result filed described above


For error API, it will return error code in error field, which is categorized by HTTP status code as rule below:

| HTTP Status | Description                                                  |
|-------------|--------------------------------------------------------------|
| 400         | Request data is invalid, such us null, empty or wrong format |
| 401         | Error related to authentication                              |
| 403         | Error related to permission                                  |
| 404         | Request resource not found                                   |
| 409         | Error related to business logic                              |
| 500         | Internal server error                                        |                                                                          |

## Format

### Date Time Format

All timestamps request/response in _**UTC**_ time, [ISO 8601](https://www.w3.org/TR/NOTE-datetime)
format: `YYYY-MM-DDThh:mm:ss.sssZ`, where:

`YYYY` : four-digit year

`MM`   : two-digit month (01=January, etc.)

`DD`  : two-digit day of month (01 through 31)

`hh` : two digits of hour (00 through 23)

`mm` :two digits of minute (00 through 59)

`ss`: two digits of second (00 through 59)

`sss`: three digit of milliseconds

`Z`   : UTC timezone
