// ARC/MorphemeStatistics.js

import { compareMorpheme } from '@/components/ARC/Morpheme.js';

function generateStatistics(morphemeList, answer, text) {
    const differences = compareMorpheme(answer, text);
    const statistics = {};
    console.log(morphemeList)
    // 모든 형태소에 대한 초기 통계 정보를 생성
    // 
    

    // 형태소 차이를 기반으로 통계 정보 업데이트
    differences.forEach(({ type, morpheme1, morpheme2 }) => {
        // morpheme1과 morpheme2를 기반으로 해당하는 letter_id 찾기
        const letter_id1 = morphemeList.find(morpheme => morpheme.letter_char === morpheme1 && morpheme.type === type)?.letter_id;
        const letter_id2 = morphemeList.find(morpheme => morpheme.letter_char === morpheme2 && morpheme.type === type)?.letter_id;

        // 찾은 letter_id가 존재할 경우 해당하는 통계 정보 업데이트
        console.log(letter_id1, letter_id2)
        if (letter_id1) {
            if (type === 'cho' || type === 'jung' || type === 'jong') {
                statistics[letter_id1].wrong_cnt++;
            } else {
                statistics[letter_id1].correct_cnt++;
            }
        }

        if (letter_id2) {
            if (type === 'cho' || type === 'jung' || type === 'jong') {
                statistics[letter_id2].wrong_cnt++;
            } else {
                statistics[letter_id2].correct_cnt++;
            }
        }
    });

    return Object.values(statistics);
}

export {
    generateStatistics
};
