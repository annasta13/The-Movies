//
//  MovieScreenContent.swift
//  iosApp
//
//  Created by Annas Surdyanto on 29/11/21.
//  Copyright © 2021 private project. All rights reserved.
//

import SwiftUI
import shared

struct MovieScreenContent: View {
    var movieList: [Movie]
    
    var body: some View {
        ForEach(movieList, id: \.movieId){movie in
            NavigationLink(destination: MovieDetailScreen(movieId: movie.movieId)){
                MovieItem(movie: movie)
            }.buttonStyle(PlainButtonStyle())
        }
    }
}

struct MovieScreenContent_Previews: PreviewProvider {
    static var previews: some View {
        MovieScreenContent(movieList: MovieResponse.companion.mocked.movieDataList)
    }
}
