import axios from "axios";

const boardCategory = async () => {
    try {
    const response = await axios.get('http://i10b208.p.ssafy.io/api/boards')    
    console.log(response)
    } catch (error){
        console.log('에러가 발생했습니다.')
    }
}   

export default {
    boardCategory
};