// ARC/MorphemeStatistics.js

import { compareMorpheme } from '@/components/ARC/Morpheme.js';

function generateStatistics(morphemeList, answer, text) {
    const differences = compareMorpheme(answer, text);
    const list = [];

    // 형태소 차이를 기반으로 통계 정보 업데이트
    differences.forEach(({ type, morpheme1, morpheme2 }) => {
        // morpheme1과 morpheme2를 기반으로 해당하는 letter_id 찾기
        const letter_id = morphemeList.find(morpheme => morpheme.letter_char === morpheme1 && morpheme.type === type)?.letter_id;
        const statistics = {};

        if (!statistics[letter_id]) {
            statistics[letter_id] = { letter_id: letter_id, correct_cnt: 0, wrong_cnt: 0 };
        }
        if (morpheme1 === morpheme2) {
            statistics[letter_id].correct_cnt++;
        } else {
            statistics[letter_id].wrong_cnt++;
        }
        list.push(statistics[letter_id]);
    });

    return Object.values(statistics);
}

export {
    generateStatistics
};
