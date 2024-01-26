<template>
	<div style="display: flex; align-items: center;">
		<span>
			<a
				@click="
					() => {
                        if (leftmostPage > 1) {
						leftmostPage -= PAGE_PER_SECTION;
						// onChangeCurPage(leftmostPage);
                        					}}
				"
				>&lt;</a
			>
		</span>
		<span class="page" v-for="page in getPaginationArray(leftmostPage)" :key="page">
			<a
				:class="{ on: page === curPage }"
				@click="
					() => {
						onChangeCurPage(page);
					}
				"
			>
				{{ page }}</a
			>
		</span>
		<span v-if="leftmostPage + PAGE_PER_SECTION <= totalPage">
			<a
				@click="
					() => {
                        // if (leftmostPage + PAGE_PER_SECTION <= totalPage){
						leftmostPage += PAGE_PER_SECTION;
						onChangeCurPage(leftmostPage);
					}
				"
				>&gt;</a
			>
		</span>
	</div>
</template>

<script>
import { ref } from 'vue';

export default {
	props: {
		list: Array,
		ITEM_PER_PAGE: Number,
		PAGE_PER_SECTION: Number,
	},
	emits: ['change-page'],
	setup(props, { emit }) {
		return {
			...usePagination(props, emit),
		};
	},
};

const usePagination = (props, emit) => {
	const curPage = ref(1);
	const leftmostPage = ref(1);
	const totalPage = Math.ceil(props.list.length / (props.ITEM_PER_PAGE || 1));

	const getPaginationArray = (left) => {
		// [left,left+1,left+2...] 로 원소의 개수를 PAGE_PER_SECTION 개 만드는 함수
		const res = [];
		for (let i = left; i < Math.min(totalPage + 1, left + props.PAGE_PER_SECTION); i++) {
			res.push(i);
		}
		return res;
	};

	const onChangeCurPage = (page) => {
		curPage.value = page;
		emit('change-page', page);
	};

	return {
		leftmostPage,
		curPage,
		totalPage,
		//
		getPaginationArray,
		onChangeCurPage,
	};
};
</script>

<style>
.page {
	padding: 1rem;
	cursor: pointer;
   
    
}
.page a.on {
	font-weight: 900;
	color: red;
}
.page a, span > a {
    cursor: pointer;
    
}

</style>