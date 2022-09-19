# URL Shortener

[Url Controller](https://github.com/furkanyesilyurt/url-shortener/blob/master/src/main/java/com/fyesilyurt/urlshortener/controller/UrlController.java)

| Method Type | URI | Request Body | Description |
| ---- | ---- | ---- | ---- |
| GET | /api/v1/shortUrl | | Get all urls. |
| GET | /api/v1/shortUrl/show | String code | Get url with url code. |
| GET | /api/v1/shortUrl/redirect | String code | Redirect url. |
| POST | /api/v1/shortUrl | ShortUrlRequestDto | Save url. |
