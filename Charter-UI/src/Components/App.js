import React from 'react';

class App extends React.Component {

    state = { tableData: [], name: '', state: '', genre: '' }

    headers = [{
        displayName: 'Name',
        field: 'NAME'
    },
    {
        displayName: 'City',
        field: 'CITY'
    },
    {
        displayName: 'State',
        field: 'STATE'
    },
    {
        displayName: 'Phone Number',
        field: 'PHONE'
    },
    {
        displayName: 'Genre',
        field: 'GENRE'
    },
    ];

    componentDidMount() {
        this.onTermSubmit();
    }

   onTermSubmit = () => {
        fetch(`http://localhost:4567/restaurants?name=${this.state.name}&state=${this.state.state}&genre=${this.state.genre}`)
            .then(res => res.json()).then(res => {
                if (res instanceof Array) {
                    this.setState({ tableData: res });
                } else {
                    this.setState({ tableData: [] });
                }
            }).catch(error => {
                this.setState({ tableData: [] });
            });
    }

    onNameChanged = event => {
        this.setState({ name: event.target.value });
    }

    onStateChanged = event => {
        this.setState({ state: event.target.value });
    }

    onGenreChanged = event => {
        this.setState({ genre: event.target.value });
    }

    render() {
        return (
            <div>
                <h1>Charter Spectrum</h1>
                <div className="container">
                    <div className="search-bar ui segment">
                        <div className="ui form">
                            <label htmlFor="name">Name : </label>
                            <input typeof="text" name="name" value={this.state.name}
                                onChange={this.onNameChanged} />
                        </div>
                        
                        
                    </div>
                    <div className="search-bar ui segment">
                        <div className="ui form">
                            <label htmlFor="state">State : </label>
                            <input typeof="text" name="state" value={this.state.state}
                                onChange={this.onStateChanged} />
                        </div>
                        
                        
                    </div>
                    <div className="search-bar ui segment">
                        <div className="ui form">
                            <label htmlFor="genre">Genre : </label>
                            <input typeof="text" name="genre" value={this.state.genre}
                                onChange={this.onGenreChanged} />
                        </div>
                        
                        
                    </div>
                    <br/>
                    <button className="ui secondary button" onClick={this.onTermSubmit}>Apply</button>
                    <br/>
                    <div className="tableContainer">
                        <table className="ui striped table overflow-auto">
                            <thead>
                                <tr>
                                    {this.headers.map(header => {
                                        return <th className="headings" key={header.field}>{header.displayName}</th>
                                    })}
                                </tr>
                            </thead>
                            <tbody>
                                {this.state.tableData.map(row => {
                                    return <tr key={row.ID}>{
                                        this.headers.map(header => <td key={header.field}><span>{row[header.field]}</span></td>)
                                    }
                                    </tr>
                                })
                                }
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        );
    }
}

export default App;