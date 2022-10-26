import { Component, OnInit } from '@angular/core';
import { Timestamp } from '../timestamp';
import { TimestampService } from '../timestamp.service';

@Component({
	selector: 'app-chart',
	templateUrl: './chart.component.html',
	styleUrls: ['./chart.component.css']
})

export class ChartComponent implements OnInit {

	constructor(private timestampService: TimestampService) { }

	timestamps: Timestamp[] = [];

	dataPoints: any = [];
	chart: any;

	ngOnInit(): void {
		//Called after the constructor, initializing input properties, and the first call to ngOnChanges.
		//Add 'implements OnInit' to the class.
		this.getTimestampsByUser('mihaighise');
	}

	getTimestampsByUser(username: string) {
		this.timestampService.getTimestampsByUser(username).subscribe(
			(response: Timestamp[]) => {
				this.timestamps = response;
				console.log(this.timestamps);
				for (let i = 0; i < this.timestamps.length; i++) {
					this.dataPoints.push({ x: new Date(this.timestamps[i].time), y: Number(this.timestamps[i].consumption) });
				}
				this.chart.subtitles[0].remove();
			}
		)
	}

	chartOptions = {
		theme: "light2",
		zoomEnabled: true,
		exportEnabled: true,
		title: {
			text: "Full consumption chart"
		},
		subtitles: [{
			text: "Loading Data...",
			fontSize: 24,
			horizontalAlign: "center",
			verticalAlign: "center",
			dockInsidePlotArea: true
		}],
		axisY: {
			title: "Consumption",
		},
		xAxes: [{
			type: 'time',
			time: {
				// round: 'day'                                                                                                                                                                            
				tooltipFormat: 'YYYY-MM-DD HH:mm'
			},
			display: true,
			scaleLabel: {
				display: true,
				labelString: 'Time'
			}
		 }],
		data: [{
			type: "line",
			name: "All devices consumption",
			dataPoints: this.dataPoints
		}]
	}

	getChartInstance(chart: object) {
		this.chart = chart;
	}
}
