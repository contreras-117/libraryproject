export const oktaConfig = {
    clientId: '0oai9te6oovcVqMmd5d7',
    issuer: 'https://dev-81572834.okta.com/oauth2/default',
    redirectUri: 'http://localhost:3000/login/callback',
    scopes: ['openId', 'profile', 'email'],
    pkce: true,
    dissableHttpsCheck: true,
}