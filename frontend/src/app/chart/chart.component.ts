import { Component, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { Timestamp } from '../timestamp';
import { TimestampService } from '../timestamp.service';

@Component({
	selector: 'app-chart',
	templateUrl: './chart.component.html',
	styleUrls: ['./chart.component.css']
})

export class ChartComponent implements OnInit, OnChanges {

	constructor(private timestampService: TimestampService) { }

	timestamps: Timestamp[] = [];

	dt!: any;

	dataPoints: any = [];
	chart: any;

	ngOnInit(): void {
		//Called after the constructor, initializing input properties, and the first call to ngOnChanges.
		//Add 'implements OnInit' to the class.
		this.getTimestampsByUser('mihaighise');
		console.log(this.dt);
	}

	ngOnChanges(changes: SimpleChanges): void {
		//Called before any other lifecycle hook. Use it to inject dependencies, but avoid any serious work here.
		//Add '${implements OnChanges}' to the class.
		console.log(this.dt);
	}


	getTimestampsByUser(username: string) {
		this.timestampService.getTimestampsByUser(username).subscribe(
			(response: Timestamp[]) => {
				this.timestamps = response;
				for (let i = 0; i < this.timestamps.length; i++) {
					this.dataPoints.push({ x: new Date(this.timestamps[i].time), y: Number(this.timestamps[i].consumption),
					label: this.timestamps[i].time });
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
		axisX: {
			title: "timeline",
			valueType: 'dateTime',
		},
		data: [{
			type: "line",
			name: "All devices consumption",
			xValues: "dateTime",
			dataPoints: this.dataPoints
		}]
	}

	getChartInstance(chart: object) {
		this.chart = chart;
	}
}
