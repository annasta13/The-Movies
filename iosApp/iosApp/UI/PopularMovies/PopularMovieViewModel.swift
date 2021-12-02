//
//  PopularMovieViewModel.swift
//  iosApp
//
//  Created by Annas Surdyanto on 29/11/21.
//  Copyright © 2021 private project. All rights reserved.
//

import Foundation
import shared

class PopularMovieViewModel: ObservableObject{
    private let sharedViewModel = MovieModule().movieSharedViewModel
    
    @Published var state = MovieViewState.companion.empty
    
    init(){
        initState()
    }
    
    func initState(){
        sharedViewModel.fetchMovie(viewState: state, type: Int32(1)).collectCommon(callback: {newState in
            self.state = newState!
        })
    }
}