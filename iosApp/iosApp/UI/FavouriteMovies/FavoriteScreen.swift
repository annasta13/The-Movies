//
//  FavoriteScreen.swift
//  iosApp
//
//  Created by Developer on 30/11/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct FavoriteScreen: View {
    @ObservedObject private var viewModel = FavouriteViewModel()
    private let refreshName = "favoriteMovies"
    
    var body: some View {
        let state = viewModel.state
        //NavigationView{
        ScrollView(.vertical){
            RefreshView(coordinateSpaceName: refreshName){viewModel.initState()}
            
            if (!state.movieResponse.isEmpty){
                Spacer().padding(.top, 40)
                ForEach(state.movieResponse, id: \.movieId){movie in
                    NavigationLink(destination: MovieDetailScreen(movieId: movie.movieId)){
                        MovieItem(movie: movie)
                    }.buttonStyle(PlainButtonStyle())
                }
            }
            else {
                VStack{
                    Text("No favourite movie yet")
                }.frame(height: UIScreen.main.bounds.height)
            }
        }.coordinateSpace(name: refreshName)
            .onAppear{viewModel.initState()}
            .navigationTitle("Favourite Movie")
            .navigationBarTitleDisplayMode(.inline)
        
    }
}

struct FavoriteScreen_Previews: PreviewProvider {
    static var previews: some View {
        FavoriteScreen()
    }
}
