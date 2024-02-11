import axios from "axios";

const baseURL = import.meta.env.VITE_BASE_URL + "/api/";

//닉네임 변경
const editNickname = async (nickname, token) => {
  console.log("nickname : " + nickname);
  const datas = {
    nickname: nickname,
  };

  return await axios.post(baseURL + "users/nickname", datas, {
    headers: {
      Authorization: token,
    },
  });
};

//이미지 변경
const editProfileImage = async (image, token) => {
  const datas = {
    image: image,
  };

  return await axios.post(baseURL + "users/profile-image", datas, {
    headers: {
      Authorization: token,
    },
  });
};

//닉네임, 이미지 변경 요청 동시에 보내기
const editUserInfo = async (nickname, image, token) => {
  const datas = {
    nickname: nickname,
    image: image,
  };

  return await axios.all([
    //닉네임 변경
    axios.post(baseURL + "users/nickname", datas, {
      headers: {
        Authorization: token,
      },
    }),

    //이미지 변경
    axios.post(baseURL + "users/nickname", datas, {
      headers: {
        Authorization: token,
      },
    }),
  ]);
};

export { editNickname, editProfileImage, editUserInfo };
