import axios from 'axios'

export const uploadImage = (file) => {
    const formData = new FormData();
    formData.append('image', file);
    return axios.post ('/api/image',formData,{
        headers: {
            'Content-Type': 'multipart/form-data'
        }
    }).then(response => response.data)
}

export const analyzeImage = (file) => {
    const formData = new FormData();
    formData.append('image', file);
    return axios.post ('/api/image/analyze',formData,{
        headers: {
            'Content-Type': 'multipart/form-data'
        }
    }).then(response =>response.data)
}
