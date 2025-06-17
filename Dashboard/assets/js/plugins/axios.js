const api = axios.create({
    baseURL: 'http://localhost:8080/',
    headers: {
        'Content-Type': 'application/json',
    },
});

api.interceptors.request.use(
    (config) => {
        const token = localStorage.getItem('jwtToken');
        if (token) {
            config.headers.Authorization = `Bearer ${token}`;
        }
        return config;
    },
    (error) => {
        return Promise.reject(error);
    }
);

api.interceptors.response.use(
    (response) => {
        return response;
    },
    (error) => {
        if (error.response && error.response.status === 401) {
            localStorage.removeItem('jwtToken');
            alert('Phiên đăng nhập đã hết hạn hoặc không hợp lệ. Vui lòng đăng nhập lại.');
            window.location.href = '/index.html';
        }
        return Promise.reject(error);
    }
);