/**
 * <pre>
 * </pre>
 * 
 * @author 길영주
 * @since 2021. 2. 25.
 * @version 1.0
 * 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2021. 2. 25.      길영주       최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 * </pre>
 */

//FULL CALLENDAR 
document.addEventListener('DOMContentLoaded', function() {
	let Calendar = FullCalendar.Calendar;

	let containerEl = document.getElementById('external-events');
	let calendarEl = document.getElementById('calendar');
	let checkbox = document.getElementById('drop-remove');

	let calendar = new FullCalendar.Calendar(calendarEl, {
		locale : 'ko',
		initialView : 'dayGridMonth',
		themeSystem : 'bootstrap',
		selectable : true,
		dayMaxEvents : true,
		headerToolbar : {
			left : 'prevYear,prev,next,nextYear today',
			center : 'title',
			right : 'dayGridMonth,dayGridWeek,dayGridDay'
		},
		editable : true,
		events : eventsArr
	});
	calendar.render();
});
//FULL CALLENDAR 끝


var ctx1 = document.getElementById('weekWorkChart').getContext('2d');
var weekWorkChart = new Chart(ctx1, {
	type : 'bar',
	data : {
		labels : [ '월', '화', '수', '목', '금', '토', '일' ],
		datasets : [ {
			label : '일주일 근무시간',
			data : [ mon, tue, wed, thu, fri, sat, sun ],
			backgroundColor : [ 'rgba(0, 184, 148,1.0)',
					'rgba(0, 206, 201,1.0)', 'rgba(129, 236, 236,1.0)',
					'rgba(108, 92, 231,1.0)', 'rgba(253, 203, 110,1.0)',
					'rgba(253, 121, 168,1.0)', 'rgba(255, 234, 167,1.0)' ],
			borderColor : [ 'rgba(0, 184, 148,1.0)', 'rgba(0, 206, 201,1.0)',
					'rgba(129, 236, 236,1.0)', 'rgba(108, 92, 231,1.0)',
					'rgba(253, 203, 110,1.0)', 'rgba(253, 121, 168,1.0)',
					'rgba(255, 234, 167,1.0)' ],
			borderWidth : 1
		} ]
	},
	options : {
		responsive : false,
		scales : {
			yAxes : [ {
				ticks : {
					min : 0,
					max : 24,
				}
			} ]
		},
		legend : {
			display : false,
		}
	}
});

var ctx2 = document.getElementById('monthWorkChart').getContext('2d');
var monthWorkChart = new Chart(
		ctx2,
		{
			type : 'doughnut',
			data : {
				datasets : [ {
					data : [ leftWorkDay, vacationDay, workDay ],
					backgroundColor : [ 'rgba(255,255,255,.3)',
							'rgba(38, 222, 129,1.0)', 'rgba(69, 170, 242,1.0)' ],
					borderColor : [ 'rgba(223, 230, 233,1.0)',
							'rgba(255,255,255,.3)', 'rgba(255,255,255,.3)' ]
				} ],
				labels : [ '잔여 일수', '휴가 사용일 수', '근무일 수' ]
			},
			options : {
				legend : {
					display : true,
					position : 'bottom',
					labels : {
						boxWidth : 20,
						fontColor : '#111',
						padding : 15
					}
				},
				tooltips : {
					titleFontSize : 16,
					titleFontColor : '#0066ff',
					bodyFontColor : '#FFF',
					bodyFontSize : 24,
					displayColors : false
				}
			}
		});