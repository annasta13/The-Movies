//
//  MovieDetailViewModel.swift
//  iosApp
//
//  Created by Annas Surdyanto on 29/11/21.
//  Copyright © 2021 private project. All rights reserved.
//

import shared

class MovieDetailViewModel : ObservableObject{
    private let sharedViewModel = MovieModule().movieDetailSharedViewModel
    
    @Published var state = MovieDetailViewState.companion.empty
    
    func initState(movieId: Int64){
        sharedViewModel.fetchDetail(viewState: state, movieId: movieId).collectCommon(){ [self] newState in
            self.state = newState!
        }
    }
    
    func setFavorite(){
        sharedViewModel.setFavorite(viewState: state).collectCommon(){ [self] newState in
            self.state = newState!
        }
    }
}
