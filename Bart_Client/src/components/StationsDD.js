import React, { Component } from 'react';
import Stations from './Stations';


class StationsDD extends Component {
    constructor() {
        super();
        this.state = {
            stations: [],
            griddata:'',
        };

    }

    componentDidMount() {
        let initialStations = [];
        fetch('http://api.bart.gov/api/stn.aspx?cmd=stns&key=MW9S-E7SL-26DU-VV8V&json=y')
            .then(response => {
                return response.json();
            }).then(data => {
            initialStations = data.root.stations.station.map((station) => {
                return station
            });
            this.setState({
                stations: initialStations,
            });
            console.log("stations - ",this.state.stations);
        });
    }


    render() {
        return (
                    <Stations state={this.state} />
        );
    }

    // getTripDetails(data){
    //     console.log("  sourceAbbr ",data.sourceAbbr)
    //     console.log("  sourceAbbr ",data.destinationAbbr)
    //     let initialStations = '';
    //     let api='http://api.bart.gov/api/sched.aspx?cmd=depart&orig='+data.sourceAbbr+'&dest='+data.destinationAbbr+'&date=now&key=MW9S-E7SL-26DU-VV8V&b=0&a=3&l=1&json=y'
    //     console.log(api);
    //     fetch(api)
    //         .then(response => {   
    //             return response.json();
    //         }).then(data => {
    //         console.log("data.root =",data.root.id);
    //         initialStations = data.root.schedule.request;
            
    //         // map((station) => {
    //         //     return station
    //         // });
    //         //let {"@amount":amount} =  initialStations[0].fares.fare    
    //         // this.setState({
    //         //     griddata: initialStations,
    //         // });
    //         //console.log("initialStations =",initialStations );
            
    //     });
    //    // {this.renderTable(this.initialStations)}
    // //    return(
           
    // //    <TripDetails data={this.initialStations} />
    // //    )
    // };
            
      

   
}

export default StationsDD;