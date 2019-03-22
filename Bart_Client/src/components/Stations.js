import React from 'react';
import { Grid, GridColumn as Column } from '@progress/kendo-react-grid'
import TripDetails from './TripDetails'
class Stations extends React.Component {
    constructor() {
        super();
        this.changeSource = this.changeSource.bind(this);
        this.chaangeDestination = this.chaangeDestination.bind(this);
        this.getTripInfo = this.getTripInfo.bind(this);

        this.state = {
            source :'',
            destination : '',
            sourceAbbr :'',
            destinationAbbr : '',
            data:[],
        };
        
    }

    render () {
        let stations = this.props.state.stations;
        let optionItems = stations.map((stations) =>
                <option selectdata-id={stations.abbr} key={stations.abbr}>{stations.name}</option>
            );

        return (
        <div>
         <div>
              <label htmlFor="make-input">
                    Seelct Source Station :  
              </label>
             <select style={{marginLeft:'10px'}} onChange={this.changeSource}>
                <option key="Source Stations">Select Source</option>
                {optionItems}
             </select>

             <label htmlFor="make-input" style={{marginLeft:'50px'}}>
                    Select Destination Station :  
              </label>
             <select style={{marginLeft:'10px'}} onChange={this.chaangeDestination}>
             <option key="Destination Stations">Select Destination</option>
                {optionItems}
             </select>

            <button type="button" onClick={this.getTripInfo}>Get Trip Details</button>
         </div>
        
        
           
           <TripDetails data={this.state.data} />
           </div>
        )
    }

    changeSource(e){
        this.setState({
            source: e.target.value,
            sourceAbbr:e.target.childNodes[e.target.selectedIndex].getAttribute('selectdata-id'),
        });

    }

    chaangeDestination(e){
        this.setState({
            destination: e.target.value,
            destinationAbbr:e.target.childNodes[e.target.selectedIndex].getAttribute('selectdata-id'),
        });

    }

    getTripInfo(){
        
           
            let initialStations = [];
            let api='http://api.bart.gov/api/sched.aspx?cmd=depart&orig='+this.state.sourceAbbr+'&dest='+this.state.destinationAbbr+'&date=now&key=MW9S-E7SL-26DU-VV8V&b=0&a=3&l=1&json=y'
           
            fetch(api)
                .then(response => {   
                    return response.json();
                }).then(mydata => {
                    initialStations = mydata.root.schedule.request.trip.map((trip) => {
                        return trip
                    });
                
                // map((station) => {
                //     return station
                // });
                //let {"@amount":amount} =  initialStations[0].fares.fare    
                // this.setState({
                //     griddata: initialStations,
                // });
                console.log("initialStations = ",initialStations)
                this.setState({
                    data: initialStations,
                  })
                  console.log("data  = ",this.state.data)
            });
          

           
           
           // {this.renderTable(this.initialStations)}
        //    return(
            
        //    //<TripDetails data={this.initialStations} />
        //    )
            };
                
        
       // this.props.getTripInfo(this.state);
    
}

export default Stations;