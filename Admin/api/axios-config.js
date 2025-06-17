const api = window.axios.create({
    baseURL: 'http://localhost:8080/',
    headers: {
        'Content-Type': 'application/json',
        'Accept': 'application/json'
    }
});

api.interceptors.request.use(
    config => {
        const token = localStorage.getItem('authToken');
        if (token) {
            config.headers.Authorization = `Bearer ${token}`;
        }
        return config;
    },
    error => {
        return Promise.reject(error);
    }
);

api.interceptors.response.use(
    response => {
        return response;
    },
    error => {
        if (error.response && error.response.status === 401) {
            console.error('Unauthorized: Token might be invalid or expired.');
            localStorage.removeItem('authToken');
            if (window.location.pathname !== '/index.html' && window.location.pathname !== '/') {
                window.location.href = '/index.html';
            }
        }
        return Promise.reject(error);
    }
);

export default api;