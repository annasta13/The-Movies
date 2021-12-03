//
//  FavoriteViewModel.swift
//  iosApp
//
//  Created by Annas Surdyanto on 29/11/21.
//  Copyright © 2021 private project. All rights reserved.
//

import shared

class FavouriteViewModel: ObservableObject{
    private let sharedViewModel = MovieModule().favoriteSharedViewModel
    
    @Published var state = FavoriteMovieViewState.companion.empty
    
    func initState(){
        sharedViewModel.getFavoriteMovie(viewState: state).collectCommon(){ newState in
            self.state = newState!
        }
    }
}
