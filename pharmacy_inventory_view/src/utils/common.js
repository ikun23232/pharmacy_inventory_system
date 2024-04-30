

export const baseImgURL = '/api/file/download?path='

export function readCookieByName(cookieName) {
    let cookie = document.cookie
    let cookies = cookie.split(';')
    for (let key in cookies) {
        let name = cookies[key].split('=')[0].trim()
        if (name === cookieName) {
            return cookies[key].split('=')[1]
        }
    }
}

export function checkImgUpload(file) {
    const isJpgOrPng = file.type === 'image/jpeg' || file.type === 'image/png';
    if (!isJpgOrPng) {
        message?.error('你应该上传jpg与png图片!');
    }
    const isLt2M = file.size / 1024 / 1024 < 2;
    if (!isLt2M) {
        message?.error('文件大小不能超过2MB!');
    }
    return isJpgOrPng && isLt2M;
}

export function createFormData(object) {
    const formData = new FormData();
    Object.keys(object).forEach(function (key) {
        formData.append(key, object[key])
    })
    return formData
}
